package com.ceshi.model;

import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ActiveUser {
    private User user;
    private List<String> roles;
    private List<String> permissions;

}
