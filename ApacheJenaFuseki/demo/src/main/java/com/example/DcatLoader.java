package com.example;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;

import java.io.InputStream;

public class DcatLoader {

    public static void main(String[] args) {
        DcatLoader loader = new DcatLoader();
        loader.loadDcatFiles();
    }

    public void loadDcatFiles() {
        // Paths to the DCAT files inside the resources folder
        String[] dcatFiles = {
            "300228-0-accidentes-trafico-detalle.dcat",
            "300110-0-accidentes-bicicleta.dcat",
            "208252-0-incidencias-viapublica-mapa.dcat"
        };

        // Create a model to hold the RDF data
        Model model = ModelFactory.createDefaultModel();

        // Load each DCAT file into the model
        for (String dcatFile : dcatFiles) {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dcatFile)) {
                if (inputStream == null) {
                    throw new IllegalArgumentException("File not found: " + dcatFile);
                }
                model.read(inputStream, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Connect to the Fuseki server and upload the model
        try (RDFConnection conn = RDFConnectionFuseki.create()
                .destination("http://localhost:3030/MadridTrafico") // Use your target dataset
                .build()) {
            conn.load(model);  // Load the model into the Fuseki dataset
        }
    }
}
