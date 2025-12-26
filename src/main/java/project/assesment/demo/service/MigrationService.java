package project.assesment.demo.service;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.InsertAllRequest;
import com.google.cloud.bigquery.TableId;
import project.assesment.demo.model.User;

import java.util.List;
import java.util.Map;

public class MigrationService {

    private final BigQuery bigQuery;
    private final String datasetName = "us-central";
    private final String tableName = "users";

    public MigrationService() {
        bigQuery = BigQueryOptions.getDefaultInstance().getService();
    }

    public void migrate(List<User> users) {
        TableId tableId = TableId.of(datasetName, tableName);

        for (User user : users) {
            InsertAllRequest.RowToInsert row = InsertAllRequest.RowToInsert.of(
                    user.getEmail(),
                    Map.of(
                            "name", user.getName(),
                            "dob", user.getDob(),
                            "email", user.getEmail(),
                            "password", user.getPassword(),
                            "phone", user.getPhone(),
                            "gender", user.getGender(),
                            "address", user.getAddress()
                    )
            );

            InsertAllRequest insertRequest = InsertAllRequest.newBuilder(tableId)
                    .addRow(row)
                    .build();
            bigQuery.insertAll(insertRequest);
        }
    }
}
