import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WeatherAnalysis {

    public static class WeatherMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        private Text yearType = new Text();
        private IntWritable temp = new IntWritable();

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] parts = value.toString().split(",");
            if (parts.length != 4) return;

            String date = parts[1];
            String year = date.substring(0, 4);
            String type = parts[2];
            int temperature;

            try {
                temperature = Integer.parseInt(parts[3]);
            } catch (NumberFormatException e) {
                return;
            }

            if (type.equals("TMAX")) {
                yearType.set("Hottest in " + year);
                temp.set(temperature);
                context.write(yearType, temp);
            } else if (type.equals("TMIN")) {
                yearType.set("Coolest in " + year);
                temp.set(temperature);
                context.write(yearType, temp);
            }
        }
    }

    public static class WeatherReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            int extreme = key.toString().startsWith("Hottest") ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (IntWritable val : values) {
                if (key.toString().startsWith("Hottest")) {
                    extreme = Math.max(extreme, val.get());
                } else {
                    extreme = Math.min(extreme, val.get());
                }
            }

            result.set(extreme);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Weather Analysis");

        job.setJarByClass(WeatherAnalysis.class);
        job.setMapperClass(WeatherMapper.class);
        job.setReducerClass(WeatherReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
