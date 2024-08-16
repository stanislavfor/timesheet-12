package ru.gb.timesheet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.TimesheetService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TimesheetControllerTest {
    private TimesheetService service;

    @InjectMocks
    private TimesheetController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTimesheetById() {
        Timesheet timesheet = new Timesheet();
        timesheet.setId(1L);

        when(service.findById(1L)).thenReturn(Optional.of(timesheet));

        ResponseEntity<Timesheet> response = controller.get(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(timesheet, response.getBody());
    }

    @Test
    void testGetTimesheetById_NotFound() {
        when(service.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Timesheet> response = controller.get(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllTimesheets() {
        Timesheet timesheet = new Timesheet();
        List<Timesheet> timesheets = Collections.singletonList(timesheet);

        when(service.findAll(any(), any())).thenReturn(timesheets);

        ResponseEntity<List<Timesheet>> response = controller.getAll(null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(timesheets, response.getBody());
    }

    @Test
    void testCreateTimesheet() {
        Timesheet timesheet = new Timesheet();
        Timesheet createdTimesheet = new Timesheet();
        createdTimesheet.setId(1L);

        when(service.create(any(Timesheet.class))).thenReturn(createdTimesheet);

        ResponseEntity<Timesheet> response = controller.create(timesheet);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdTimesheet, response.getBody());
    }

    @Test
    void testDeleteTimesheet() {
        doNothing().when(service).delete(anyLong());

        ResponseEntity<Void> response = controller.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testUpdateTimesheet() {
        Timesheet timesheet = new Timesheet();
        timesheet.setId(1L);
        Timesheet updatedTimesheet = new Timesheet();
        updatedTimesheet.setId(1L);

        when(service.update(anyLong(), any(Timesheet.class))).thenReturn(updatedTimesheet);

        ResponseEntity<Timesheet> response = controller.update(1L, timesheet);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTimesheet, response.getBody());
    }

    @Test
    void testHandleNoSuchElementException() {
        ResponseEntity<?> response = controller.handleNoSuchElementException(new NoSuchElementException());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
