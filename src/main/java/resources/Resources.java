package resources;

public final class Resources {
        public final static String RES_ADD = "/maps/api/place/add/json";
        public final static String RES_DELETE = "/maps/api/place/delete/json";
        public final static String RES_ADDX = "/maps/api/place/add/xml";

        public final static String LIB_RES_ADD = "Library/Addbook.php";
        public final static String JIRA_SESSION = "rest/auth/1/session";
        public final static String J_CREATE_ISSUE = "rest/api/2/issue";

        public static String addComment(String key){
                String resource = "rest/api/2/issue/" + key + "/comment";
                return resource;
        }

        public static String updateComment(String key, String issue){
                String resource = "rest/api/2/issue/" + key + "/comment/"+issue;
                return resource;
        }
}
