package com.mob.mubai.data.bean;

/**
 * Created by lzw on 2016/12/16.
 */

public class LoginValue {
        private String name;
        private String age;
        private String birthday;
        private String email;
        private String id;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getAge() {
                return age;
        }

        public void setAge(String age) {
                this.age = age;
        }

        public String getBirthday() {
                return birthday;
        }

        public void setBirthday(String birthday) {
                this.birthday = birthday;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }
}
