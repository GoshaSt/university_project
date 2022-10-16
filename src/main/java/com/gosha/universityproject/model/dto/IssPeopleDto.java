package com.gosha.universityproject.model.dto;

import com.gosha.universityproject.entity.People;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter

public class IssPeopleDto {

    List<People> people;

}
