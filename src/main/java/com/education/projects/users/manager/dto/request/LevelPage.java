package com.education.projects.users.manager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class LevelPage extends CommonPage{
   public LevelPage(){
       super("levelDescr");
   }
    @NotBlank
    @Schema(name = "sortBy",
            description = "Sorting by levelDescr",
            example = "levelDescr")
    private String sortBy = "levelDescr";

}
