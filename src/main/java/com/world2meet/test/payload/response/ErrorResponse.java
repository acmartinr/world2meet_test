package com.world2meet.test.payload.response;

import com.world2meet.test.utils.Constants;



public class ErrorResponse {
    //Response message
    private String message;

    private int errorCode;

    public ErrorResponse(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        switch (getErrorCode()) {
            case Constants.NOT_SUPER_HEROES_FOUND_CODE:
                return Constants.NOT_SUPER_HEROES_FOUND_MSG;
            case Constants.INCORRECT_DATE_FORMAT_CODE:
                return Constants.INCORRECT_DATE_FORMAT;
            case Constants.ERROR_GETTING_PRICES_CODE:
                return Constants.ERROR_GETTING_PRICES;
            case Constants.MISSING_PARAM_FIELDS_CODE:
                return Constants.MISSING_PARAM_FIELDS;
            case Constants.INCORRECT_FORMAT_FIELDS_CODE:
                return Constants.INCORRECT_FORMAT_FIELDS;
            case Constants.UPDATE_ERROR_CODE:
                return Constants.UPDATE_ERROR_CODE_MSG;
            case Constants.NOT_FILTER_SUPER_HEROES_FOUND_CODE:
                return Constants.NOT_FILTER_SUPER_HEROES_FOUND_MSG;
            default:
                setErrorCode(Constants.UNKNOWN_ERRROR_CODE);
                return Constants.UNKNOWN_ERRROR;
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
