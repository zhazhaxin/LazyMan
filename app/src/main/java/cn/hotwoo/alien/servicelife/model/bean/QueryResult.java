package cn.hotwoo.alien.servicelife.model.bean;

/**
 * Created by alien on 2015/8/18.
 */
public class QueryResult {

    private String showapi_res_error;
    private Body showapi_res_body;
    private int showapi_res_code;

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public void setShowapi_res_body(Body showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public Body getBody() {
        return showapi_res_body;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public class Body {

        private String query;
        private String[] translation;
        private Basic basic;

        public void setQuery(String query) {
            this.query = query;
        }

        public void setTranslation(String[] translation) {
            this.translation = translation;
        }

        public void setBasic(Basic basic) {
            this.basic = basic;
        }

        public String getQuery() {
            return query;
        }

        public String[] getTranslation() {
            return translation;
        }

        public Basic getBasic() {
            return basic;
        }

        public class Basic {

            private String[] explains;

            public void setExplains(String[] explains) {
                this.explains = explains;
            }

            public String[] getExplains() {
                return explains;
            }
        }
    }
}
