package com.diagnostico.api.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class DiagnosisExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String msgUser = messageSource.getMessage("msg.unknown-attributes", null,
				LocaleContextHolder.getLocale());
		String msgDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> errors = Arrays.asList(new Error(msgUser, msgDev, null));
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> errors = createListErrors(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msgUser = messageSource.getMessage("msg.method-not-supported", null,
				LocaleContextHolder.getLocale());
		String msgDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> errors = Arrays.asList(new Error(msgUser, msgDev, null));
		return handleExceptionInternal(ex, errors, headers, HttpStatus.METHOD_NOT_ALLOWED, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msgUser = messageSource.getMessage("msg.unsupported-media-type", null,
				LocaleContextHolder.getLocale());
		String msgDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> errors = Arrays.asList(new Error(msgUser, msgDev, null));
		return handleExceptionInternal(ex, errors, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String msgUser = messageSource.getMessage("feature.not-found", null,
				LocaleContextHolder.getLocale());
		String msgDev = ex.toString();
		List<Error> erros = Arrays.asList(new Error(msgUser, msgDev, null));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		String msgUser = messageSource.getMessage("feature.not-allow", null,
				LocaleContextHolder.getLocale());
		String msgDev = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> errors = Arrays.asList(new Error(msgUser, msgDev, null));
		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
	
	@ExceptionHandler(value = { InvalidFormatException.class })
	public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex) {
		String msgUser = messageSource.getMessage("invalid-format", null, LocaleContextHolder.getLocale());
		String msgDev = ex.toString();
		List<Error> errors = Arrays.asList(new Error(msgUser, msgDev, ex.getPathReference()));
		return ResponseEntity.unprocessableEntity().body(errors);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value = { TransactionSystemException.class })
	public ResponseEntity<Object> handleTxException(TransactionSystemException ex) {

		String msgUser = "Erro de Transação no sistema";
		String msgDev = "Erro de Transação no sistema";
	
		Throwable t = ex.getRootCause();
		if (t instanceof ConstraintViolationException) {
			
			ConstraintViolationException cve = (ConstraintViolationException) t;
			Set violations = cve.getConstraintViolations();
			List<Error> errors = new ArrayList<>();
			for (Object violation : violations) {
				violation = (ConstraintViolation) violation;
				msgUser = "Erro de validação dos dados";
				msgDev = t.getMessage();
				String attributeError = ((ConstraintViolation) violation).getPropertyPath().toString();

				errors.add(new Error(msgUser, msgDev, attributeError));
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		} else {
			List<Error> errors = Arrays.asList(new Error(msgUser, msgDev, null));
			return ResponseEntity.unprocessableEntity().body(errors);
		}
	}
	
	public List<Error> createListErrors(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String msgUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String msgDev = fieldError.toString();
			errors.add(new Error(msgUser, msgDev, fieldError.toString()));
		}

		return errors;
	}

	public static class Error {

		private String msgUser;
		private String msgDev;
		private String atributteError;

		public Error(String msgUser, String msgDev, String atributteError) {
			this.msgUser = msgUser;
			this.msgDev = msgDev;
			this.atributteError = atributteError;
		}

		public String getMsgUser() {
			return msgUser;
		}

		public String getMsgDev() {
			return msgDev;
		}
		
		public String getAttributeError() {
			return atributteError;
		}

	}
	
}
