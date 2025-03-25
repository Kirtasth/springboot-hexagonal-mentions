package com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.infrastructure;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.model.Course;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.domain.DataRow;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.model.GivenClass;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DataRowMapper {

    @Mapping(source = "professorId", target = "id")
    @Mapping(source = "professorName", target = "name")
    @Mapping(target = "discordName", constant = "Discord")
    @Mapping(target = "professorRole", constant = "Normal")
    Professor getProfessor(DataRow dataRow);

    @Mapping(source = "courseId", target = "id")
    @Mapping(source = "courseName", target = "name")
    Course getCourse(DataRow dataRow);

    @Mapping(source = "dataRow.givenClassId", target = "id")
    GivenClass getGivenClass(DataRow dataRow, Professor professor, Course course);
}
