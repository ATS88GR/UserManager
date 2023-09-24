package com.education.projects.users.manager.service;

import com.education.projects.users.manager.dto.request.LevelPage;
import com.education.projects.users.manager.dto.request.LevelSearchCriteria;
import com.education.projects.users.manager.dto.response.LevelDtoResp;
import com.education.projects.users.manager.entity.Level;
import com.education.projects.users.manager.exception.LevelNotFoundException;
import com.education.projects.users.manager.mapper.LevelMapper;
import com.education.projects.users.manager.repository.LevelCriteriaRepository;
import com.education.projects.users.manager.repository.LevelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LevelServiceImplTest {
    @Mock
    private LevelRepository levelRepository;
    @Mock
    private LevelMapper levelMapper;
    @Mock
    private LevelCriteriaRepository levelCriteriaRepository;

    @InjectMocks
    private LevelServiceImpl levelService;

    /**
     * Tests for a successful result of getting a Level by id
     *
     * @throws Exception
     */
    @Test
    void getLevelById() throws Exception {
        UUID testUUID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

        Level level = new Level();
        level.setId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        level.setLevelDescr("phd");

        UUID expectedUUID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
        String expectedLevel = "phd";

        when(levelRepository.existsById(testUUID)).thenReturn(true);
        when(levelRepository.getReferenceById(testUUID)).thenReturn(level);
        Level testResult = levelService.getLevelById(testUUID);

        assertEquals(expectedUUID, testResult.getId());
        assertEquals(expectedLevel, testResult.getLevelDescr());
    }

    /**
     * Tests for an unsuccessful result of getting a Level by id
     */
    @Test
    void getLevelByIdOnFail() {
        UUID testUUID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

        when(levelRepository.existsById(testUUID)).thenReturn(false);

        assertThrows(LevelNotFoundException.class, () -> levelService.getLevelById(testUUID));
    }

    /**
     * Tests for a successful result of getting all Levels
     *
     * @throws Exception
     */
    @Test
    void getAllLevels() throws Exception {
        List<LevelDtoResp> levelRespExp = new ArrayList<>();
        levelRespExp.add(
                new LevelDtoResp(UUID.fromString("0994da2b-8439-4843-80e6-69ee7489a3b7"),
                        "bad"));
        levelRespExp.add(
                new LevelDtoResp(UUID.fromString("7287e720-6507-4349-893f-42b41ac5b4ac"),
                        "medium"));


        List<LevelDtoResp> levelResp = new ArrayList<>();
        levelResp.add(
                new LevelDtoResp(UUID.fromString("0994da2b-8439-4843-80e6-69ee7489a3b7"),
                        "bad"));
        levelResp.add(
                new LevelDtoResp(UUID.fromString("7287e720-6507-4349-893f-42b41ac5b4ac"),
                        "medium"));


        List<Level> levelList = new ArrayList<>();
        levelList.add(
                new Level(UUID.fromString("0994da2b-8439-4843-80e6-69ee7489a3b7"),
                        "bad"));
        levelList.add(
                new Level(UUID.fromString("7287e720-6507-4349-893f-42b41ac5b4ac"),
                        "medium"));


        when(levelRepository.findAll()).thenReturn(levelList);
        when(levelMapper.levelListToLevelDtoList(levelList)).thenReturn(levelResp);

        assertEquals(levelRespExp, levelService.getAllLevels());
    }

    /**
     * Tests for a successful result of getting a Level dto by id
     *
     * @throws Exception
     */
    @Test
    void getLevelDtoById() throws Exception {
        UUID testUUID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
        LevelDtoResp levelDtoRespTest = new LevelDtoResp();
        levelDtoRespTest.setId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        levelDtoRespTest.setLevelDescr("phd");

        LevelDtoResp levelDtoRespExp = new LevelDtoResp();
        levelDtoRespExp.setId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        levelDtoRespExp.setLevelDescr("phd");

        Level level = new Level();
        level.setId(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"));
        level.setLevelDescr("phd");

        UUID expectedUUID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
        String expectedLevelDescr = "phd";

        when(levelRepository.existsById(testUUID)).thenReturn(true);
        when(levelRepository.getReferenceById(testUUID)).thenReturn(level);
        when(levelMapper.levelToLevelDto(level)).thenReturn(levelDtoRespTest);
        LevelDtoResp testResult = levelService.getLevelDtoById(testUUID);

        verify(levelRepository).existsById(testUUID);
        verify(levelRepository).getReferenceById(testUUID);
        verify(levelMapper).levelToLevelDto(any());

        assertEquals(expectedUUID, testResult.getId());
        assertEquals(expectedLevelDescr, testResult.getLevelDescr());
    }

    /**
     * Tests for an unsuccessful result of getting a Level dto by id
     */
    @Test
    void getLevelDtoByIdOnFail(){
        UUID testUUID = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

        when(levelRepository.existsById(testUUID)).thenReturn(false);

        assertThrows(LevelNotFoundException.class, () -> levelService.getLevelDtoById(testUUID));
    }

    /**
     * Tests for a successful result of getting a Page Level list
     *
     * @throws Exception
     */
    @Test
    void getSortFilterPaginLevels() throws Exception {
        Page<LevelDtoResp> pageLevelDtoRespExp = mock(Page.class);
        LevelPage levelPage = new LevelPage();
        LevelSearchCriteria levelSearchCriteria = new LevelSearchCriteria("phd");

        when(levelCriteriaRepository.findAllWithFilters(levelPage, levelSearchCriteria)).thenReturn(pageLevelDtoRespExp);

        assertEquals(pageLevelDtoRespExp, levelService.getSortFilterPaginLevels(levelPage, levelSearchCriteria));
    }
}