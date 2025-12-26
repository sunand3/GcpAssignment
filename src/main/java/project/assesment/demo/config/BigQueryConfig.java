package project.assesment.demo.config;


import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;

public class BigQueryConfig {

    private static final BigQuery bigQuery =
            BigQueryOptions.getDefaultInstance().getService();

    public static BigQuery getBigQuery() {
        return bigQuery;
    }
}
