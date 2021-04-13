package ru.mart.ofd.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "incurrect input")
public class NotFoundException extends RuntimeException {

}
