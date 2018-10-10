package com.adolphchris.android.guardiannews.Utils;

public class Constants {

    /**
     * Create a private constructor because no one should ever create a {@link Constants} object.
     */
    private Constants() {
    }
    /**  Extract the key associated with the JSONObject */
    static final String JSON_KEY_RESPONSE = "response";
    static final String JSON_KEY_RESULTS = "results";
    static final String JSON_KEY_WEB_TITLE = "webTitle";
    static final String JSON_KEY_WEB_PUBLICATION_DATE = "webPublicationDate";
    static final String JSON_KEY_WEB_URL = "webUrl";
    static final String JSON_KEY_TAGS = "tags";
    static final String JSON_KEY_FIELDS = "fields";
    static final String JSON_KEY_THUMBNAIL = "thumbnail";

    /** Read timeout for setting up the HTTP request */
    static final int READ_TIMEOUT = 10000; /* milliseconds */

    /** Connect timeout for setting up the HTTP request */
    static final int CONNECT_TIMEOUT = 15000; /* milliseconds */

    /** Request method type "GET" for reading information from the server */
    static final String REQUEST_METHOD_GET = "GET";

    /** Parameters */
    public static final String QUERY_PARAM = "q";
    public static final String ORDER_BY_PARAM = "order-by";
    public static final String PAGE_SIZE_PARAM = "page-size";
    public static final String NEWEST_PARAM = "newest";
    public static final String NEWS_QUERY_NUM_PARAM = "50";
    public static final String SHOW_FIELDS_PARAM = "show-fields";
    public static final String CONTRIBUTOR_PARAM = "contributor";
    public static final String SHOW_TAGS_PARAM = "show-tags";
    public static final String API_KEY_PARAM = "api-key";

    /** API Key */
    public static final String API_KEY = "c0fcc3fb-4ea5-46bd-b0e6-d509c95d8665";

}


