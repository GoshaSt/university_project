package com.gosha.universityproject.model.dto;

import com.gosha.universityproject.entity.DataLocation;
import lombok.Data;

import java.util.List;

@Data
public class PlaceDto {

    List<DataLocation> data;

}
