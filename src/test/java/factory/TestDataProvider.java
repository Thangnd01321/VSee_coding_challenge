package factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import model.MChatMessage;
import model.MPatient;
import model.MProvider;

public class TestDataProvider {
  private static final Path DATA_FOLDER = Paths.get("src/test/resources/data");
  private static final String USERS_FILE = "users.json";
  private static final String CHAT_LOG_FILE = "chat_log.json";
  private static final List<MProvider> PROVIDERS = loadProviders();
  private static final List<MPatient> PATIENTS = loadPatients();
  private static final List<MChatMessage> CHAT_MESSAGES = loadChatMessages();

  private static List<MProvider> loadProviders() {
    ObjectMapper mapper = new ObjectMapper();
    // Read JSON file and convert to list of users
    try {
      // Read JSON file and convert to list of users
      byte[] jsonData = Files.readAllBytes(Paths.get(DATA_FOLDER.toString(), USERS_FILE));
      JsonNode node = mapper.readTree(jsonData);

      return mapper.readValue(
          node.get("providers").toString(), new TypeReference<List<MProvider>>() {});

    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  public static List<MChatMessage> getChatMessages(){
    return CHAT_MESSAGES;
  }

  private static List<MPatient> loadPatients() {
    ObjectMapper mapper = new ObjectMapper();
    // Read JSON file and convert to list of users
    try {
      // Read JSON file and convert to list of users
      byte[] jsonData = Files.readAllBytes(Paths.get(DATA_FOLDER.toString(), USERS_FILE));
      JsonNode node = mapper.readTree(jsonData);

      return mapper.readValue(
          node.get("patients").toString(), new TypeReference<List<MPatient>>() {});

    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  private static List<MChatMessage> loadChatMessages() {
    ObjectMapper mapper = new ObjectMapper();
    // Read JSON file and convert to list of users
    try {
      // Read JSON file and convert to list of users
      byte[] jsonData = Files.readAllBytes(Paths.get(DATA_FOLDER.toString(), CHAT_LOG_FILE));
      JsonNode node = mapper.readTree(jsonData);

      return mapper.readValue(
          node.get("chat_log").toString(), new TypeReference<List<MChatMessage>>() {});

    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  public static MProvider getProvider(String id) {
    return PROVIDERS.stream()
        .filter(provider -> provider.getId().equalsIgnoreCase(id))
        .findFirst()
        .orElse(null); // or throw an exception if the provider is not found
  }

  public static MPatient getPatient(String id) {
    return PATIENTS.stream()
        .filter(patient -> patient.getId().equalsIgnoreCase(id))
        .findFirst()
        .orElse(null); // or throw an exception if the provider is not found
  }
}
