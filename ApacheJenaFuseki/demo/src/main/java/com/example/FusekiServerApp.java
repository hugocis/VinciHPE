package com.example;

import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.query.Dataset;
import org.apache.jena.tdb2.TDB2Factory;

public class FusekiServerApp {
    public static void main(String[] args) {
        // Define the dataset directory
        String datasetDirectory = "databases/tdb";

        // Create a TDB2-backed dataset
        Dataset dataset = TDB2Factory.connectDataset(datasetDirectory);

        // Create and start the Fuseki server
        FusekiServer server = FusekiServer.create()
            .port(3030)
            .add("/ds", dataset)
            .build();
        
        server.start();
        System.out.println("Fuseki server running on http://localhost:3030/");
    }
}
