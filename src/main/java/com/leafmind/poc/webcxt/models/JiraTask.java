package com.leafmind.poc.webcxt.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(of = "id")
@Data public class JiraTask {
    String id;
    String name;
    String status;
    Date updated;
}
