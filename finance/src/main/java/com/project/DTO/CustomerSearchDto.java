package com.project.DTO;

import com.project.helper.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearchDto {
    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;
}
