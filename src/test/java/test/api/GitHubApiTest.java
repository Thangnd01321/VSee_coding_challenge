package test.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.testng.annotations.Test;
import utils.logging.Log;

public class GitHubApiTest {

  private static final String BASE_URL = "https://api.github.com";
  private static final String ORG_NAME = "SeleniumHQ";

  @Test
  public void testGitCountOpenIssues() throws JsonProcessingException {
    // Get all repositories of the organization
    Response response =
        RestAssured.given()
            .contentType(ContentType.JSON)
            .when()
            .get(BASE_URL + "/orgs/" + ORG_NAME + "/repos")
            .then()
            .extract()
            .response();

    List<Map<String, Object>> repos = response.jsonPath().getList("");

    int[] totalOpenIssues = {
      0
    }; // Use an array to store the total open issues since it is effectively final in the lambda

    repos.forEach(
        repo -> {
          int openIssuesCount = (Integer) repo.get("open_issues_count");
          Log.action(
              "Repo "
                  + repo.get("full_name").toString()
                  + " has "
                  + openIssuesCount
                  + " open issues");
          totalOpenIssues[0] += openIssuesCount;
        });

    Log.action("Total open issues across all repositories: " + totalOpenIssues[0]);
  }

  @Test
  public void testGitSortByDateUpdated() throws JsonProcessingException {
    // Get all repositories of the organization
    Response response =
        RestAssured.given()
            .contentType(ContentType.JSON)
            .when()
            .get(BASE_URL + "/orgs/" + ORG_NAME + "/repos")
            .then()
            .extract()
            .response();

    List<Map<String, Object>> repos = response.jsonPath().getList("");

    List<Map<String, Object>> sortedRepos =
        repos.stream()
            .sorted(
                (repo1, repo2) -> {
                  String date1 = (String) repo1.get("updated_at");
                  String date2 = (String) repo2.get("updated_at");
                  return date2.compareTo(date1);
                })
            .collect(Collectors.toList());
    Log.action("Repositories sorted by update date (descending):");
    sortedRepos.forEach(
        repo -> Log.action("Repo " + repo.get("name") + " updated at " + repo.get("updated_at")));
  }

  @Test
  public void testGitMostWatcher() throws JsonProcessingException {
    // Get all repositories of the organization
    Response response =
        RestAssured.given()
            .contentType(ContentType.JSON)
            .when()
            .get(BASE_URL + "/orgs/" + ORG_NAME + "/repos")
            .then()
            .extract()
            .response();

    List<Map<String, Object>> repos = response.jsonPath().getList("");

    // c. Find the repository with the most watchers
    Map<String, Object> repoWithMostWatchers =
        repos.stream()
            .max(
                (repo1, repo2) -> {
                  int watchers1 = (Integer) repo1.get("watchers_count");
                  int watchers2 = (Integer) repo2.get("watchers_count");
                  return Integer.compare(watchers1, watchers2);
                })
            .orElseThrow(() -> new RuntimeException("No repositories found"));

    Log.action(
        "Repository with the most watchers: "
            + repoWithMostWatchers.get("name")
            + " with "
            + repoWithMostWatchers.get("watchers_count")
            + " watchers");
  }
}
