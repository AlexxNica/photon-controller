/*
 * Copyright 2015 VMware, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.vmware.photon.controller.api.model.constraints;

import com.google.inject.Inject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validates if the given string is a valid a URL.
 */
public class UrlValidator implements ConstraintValidator<Url, String> {

  private org.apache.commons.validator.routines.UrlValidator urlValidator =
      new org.apache.commons.validator.routines.UrlValidator(new String[]{"http", "https"});

  @Inject
  public UrlValidator() {
  }

  @Override
  public void initialize(Url constraintAnnotation) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return false;
    }

    if (!urlValidator.isValid(value)) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(String.format("%s is invalid URL Address", value))
          .addConstraintViolation();
      return false;
    }

    return true;
  }
}
