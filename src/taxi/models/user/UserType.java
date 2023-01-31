package taxi.models.user;

public class UserType {
    private String userType;

    public UserType() {
        this.userType = "casual";
    }

    public void updateUserType(double rating) {
        if (rating >= 3.5)
            userType = "top";
        if (rating >= 4.0)
            userType = "vip";
        if (rating >= 4.5)
            userType = "premium";
    }

    public String getUserType() {
        return userType;
    }
}
