package com.amaysim.coding.data.clients.web.response;

import java.util.List;
import java.util.Map;

public class SampleResponse {

    public SampleData data;
    public List<SampleIncluded> included;

    public class SampleData {

        public String type;
        public String id;
        public Map<String, String> attributes;
    }

    public class SampleIncluded {
        public String type;
        public String id;
        public Map<String, String> attributes;
    }
}