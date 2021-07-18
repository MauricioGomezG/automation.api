package models;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import dtos.ProfileDTO;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class BaseModel {
    protected final ObjectMapper mapper;
    protected final Logger logger;
    protected String yamlUserFilePath = "src/main/test/resources";
    protected final ObjectMapper mapperY;

    protected BaseModel() {
        mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        logger = LoggerFactory.getLogger(this.getClass());
        mapperY = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        mapperY.findAndRegisterModules();
        mapperY.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    protected boolean isSuccessfulResponse(Response response) {
        boolean isSuccess = response.statusCode() == 200 || response.statusCode() == 201;
        if (!isSuccess) {
            logger.error("API call was not successful. \n" + response.prettyPrint());
        } else {
            System.out.println("\n------------------ Service Response Start ------------------\n");
            response.prettyPrint();
            System.out.println("\n------------------ Service Response End ------------------\n");
        }
        return isSuccess;
    }

    protected ProfileDTO getUserFromYaml(String user){
        ProfileDTO userFromFile = new ProfileDTO();
        try {
            userFromFile = mapperY.readValue(new File(String.format("%s/%s.yaml", yamlUserFilePath, user)),ProfileDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userFromFile;
    }

    public ObjectNode generateKeyValueRequest(String key, String value){
        ObjectNode node = mapper.createObjectNode()
                .put("key", key)
                .put("value", value);
        return node;
    }

    public ObjectNode generateKeyValueRequest(String key, int value){
        ObjectNode node = mapper.createObjectNode()
                .put("key", key)
                .put("value", value);
        return node;
    }

    public ObjectNode generateKeyValueRequest(String key, boolean value){
        ObjectNode node = mapper.createObjectNode()
                .put("key", key)
                .put("value", value);
        return node;
    }
}
