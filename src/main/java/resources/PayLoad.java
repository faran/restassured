package resources;

public final class PayLoad {

    public static final String bodyPost = "{\n" +
            "    \"location\":{\n" +
            "        \"lat\" : -38.383494,\n" +
            "        \"lng\" : 33.427362\n" +
            "    },\n" +
            "    \"accuracy\":50,\n" +
            "    \"name\":\"Frontline house\",\n" +
            "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
            "    \"address\" : \"29, side layout, cohen 09\",\n" +
            "    \"types\": [\"shoe park\",\"shop\"],\n" +
            "    \"website\" : \"http://google.com\",\n" +
            "    \"language\" : \"French-IN\"\n" +
            "}";

    public static String addBook(String aisle, String isbn){

        String bodyPostAddLibrary = "{\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foefoe\"\n" +
                "}";

        return bodyPostAddLibrary;
    }

    public static final String jiraSessionPost = "{ \"username\": \"ratester\", \"password\": \"ratester\" }";

    public static final String JIRACREATEISSUEBODY = "{\n" +
            " \"fields\": {\n" +
            "    \"project\": {\n" +
            "    \t\"key\": \"RAT\"\n" +
            "    \t},\n" +
            "        \"summary\": \"Creating bug from Rest Assured number 2\",\n" +
            "        \"description\": \"Creating bug from automation using Rest Assured and java number 2\", \n" +
            "        \"issuetype\": {\n" +
            "        \t\"name\":\"Bug\"\n" +
            "        }\n" +
            "\t}\n" +
            "}";

    public static final String JIRA_ADD_COMMENT = "{\n" +
            "\t\"body\": \"Updating comment from REst Assured \",\n" +
            "  \"visibility\": {\n" +
            "    \"type\": \"role\",\n" +
            "    \"value\": \"Administrators\"\n" +
            "  }\n" +
            "}";

    public static final String JIRA_UPDATE_COMMENT = "{\n" +
            "\t\"body\": \"Updating comment from REST Assured \",\n" +
            "  \"visibility\": {\n" +
            "    \"type\": \"role\",\n" +
            "    \"value\": \"Administrators\"\n" +
            "  }\n" +
            "}";
}
