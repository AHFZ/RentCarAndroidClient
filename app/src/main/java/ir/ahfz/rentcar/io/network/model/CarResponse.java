package ir.ahfz.rentcar.io.network.model;

import java.util.List;

import ir.ahfz.rentcar.io.network.BaseResponse;

public class CarResponse extends BaseResponse {

    private List<Car> cars = null;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public static class Car {

        private String id;
        private String modelId;
        private String fuel;
        private String registration;
        private Object color;
        private String year;
        private String capacity;
        private String isAutomatic;
        private Object equipment;
        private String _class;
        private String type;
        private String minAge;
        private String pricePerDay;
        private String img;
        private String branchID;
        private String createdAt;
        private String updatedAt;
        private String make;
        private String model;
        private String branch;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModelId() {
            return modelId;
        }

        public void setModelId(String modelId) {
            this.modelId = modelId;
        }

        public String getFuel() {
            return fuel;
        }

        public void setFuel(String fuel) {
            this.fuel = fuel;
        }

        public String getRegistration() {
            return registration;
        }

        public void setRegistration(String registration) {
            this.registration = registration;
        }

        public Object getColor() {
            return color;
        }

        public void setColor(Object color) {
            this.color = color;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getCapacity() {
            return capacity;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        public String getIsAutomatic() {
            return isAutomatic;
        }

        public void setIsAutomatic(String isAutomatic) {
            this.isAutomatic = isAutomatic;
        }

        public Object getEquipment() {
            return equipment;
        }

        public void setEquipment(Object equipment) {
            this.equipment = equipment;
        }

        public String getClass_() {
            return _class;
        }

        public void setClass_(String _class) {
            this._class = _class;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMinAge() {
            return minAge;
        }

        public void setMinAge(String minAge) {
            this.minAge = minAge;
        }

        public String getPricePerDay() {
            return pricePerDay;
        }

        public void setPricePerDay(String pricePerDay) {
            this.pricePerDay = pricePerDay;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getBranchID() {
            return branchID;
        }

        public void setBranchID(String branchID) {
            this.branchID = branchID;
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

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

    }
}
