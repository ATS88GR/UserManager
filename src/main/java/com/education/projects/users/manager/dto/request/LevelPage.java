package com.education.projects.users.manager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

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
