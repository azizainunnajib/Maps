package com.example.azizainun.maps;

/**
 * Created by aziza on 11/25/2017.
 */

public class Data_ig {
    private Images images;
    private User user;

    private Caption caption;

    public Images getImages() {
        return images;
    }

    public User getUser() {
        return user;
    }

    public class Caption {
        private String text;

        public String getText() {
            return text;
        }
    }

    public class User {

        private String profile_picture;

        private String full_name;

        public String getProfile_picture() {
            return profile_picture;
        }

        public String getFull_name() {
            return full_name;
        }
    }

    public class Images {

        private Standard_resolution standard_resolution;

        public Standard_resolution getStandard_resolution() {
            return standard_resolution;
        }

        public class Standard_resolution {

            private String url;

            public String getUrl() {
                return url;
            }
        }
    }
}
