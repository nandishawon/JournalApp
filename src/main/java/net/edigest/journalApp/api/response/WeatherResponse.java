package net.edigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;



@Data
public class WeatherResponse {
    private Current current;

   @Data
    public static class Current {
        @JsonProperty("last_updated_epoch")
        private int lastUpdatedEpoch;
        @JsonProperty("last_updated")
        private String lastUpdated;
        @JsonProperty("temp_c")
        private int temperature;
        private Condition condition;
        @JsonProperty("feelslike_c")
        public double feelsLike;


       @Data
        public class Condition {
            private String text;
            private String icon;
            private int code;
        }
    }
}
