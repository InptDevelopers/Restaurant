package org.inptdeveloppers.restaurantservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
private Long id;
private String name;
private String email;
}
