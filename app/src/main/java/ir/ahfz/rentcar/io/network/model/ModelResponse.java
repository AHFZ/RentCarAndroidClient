package ir.ahfz.rentcar.io.network.model;

import java.util.List;

import ir.ahfz.rentcar.io.network.BaseResponse;

public class ModelResponse extends BaseResponse {


    private List<Model> models = null;

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public static class Model {

        private String id;
        private String makeId;
        private String model;
        private String createdAt;
        private String updatedAt;
        private String make;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMakeId() {
            return makeId;
        }

        public void setMakeId(String makeId) {
            this.makeId = makeId;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

    }
}
