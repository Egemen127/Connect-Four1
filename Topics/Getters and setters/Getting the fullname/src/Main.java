class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        // write your code here
        if (firstName != null && firstName != "") {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        // write your code here
        if (lastName != null && lastName != "") {
            this.lastName = lastName;
        }
    }

    public String getFullName() {
        if ((this.firstName == null || this.firstName == "") && (this.lastName == null || this.lastName == "")) {
            return "Unknown";
        }
        if (this.firstName == null && this.firstName == "") {
            return this.lastName;
        }
        if (this.lastName == null && this.lastName == "") {
            return this.firstName;
        }
        return this.firstName + " " + this.lastName; // write your code here
    }
}
