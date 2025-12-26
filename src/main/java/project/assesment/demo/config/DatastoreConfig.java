package project.assesment.demo.config;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

public class DatastoreConfig {

    private static final Datastore datastore =
            DatastoreOptions.getDefaultInstance().getService();

    public static Datastore getDatastore() {
        return datastore;
    }
}
