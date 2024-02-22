package org.inptdeveloppers.restaurantservice.exceptions;

public class RestaurantNotFoundException extends Exception{
    public RestaurantNotFoundException(String message){
        super(message);
    }
}
