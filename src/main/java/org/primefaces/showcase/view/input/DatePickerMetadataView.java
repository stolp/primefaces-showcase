/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.view.input;

import java.io.Serializable;
import java.time.LocalDate;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.DateViewChangeEvent;
import org.primefaces.model.datepicker.DateMetadataModel;
import org.primefaces.model.datepicker.DefaultDateMetadata;
import org.primefaces.model.datepicker.DefaultDateMetadataModel;
import org.primefaces.model.datepicker.LazyDateMetadataModel;

@Named
@ViewScoped
public class DatePickerMetadataView implements Serializable {

    private LocalDate date1;
    private LocalDate date2;
    private LocalDate date3;
    private LocalDate date4;
    private final DateMetadataModel model;
    private final DateMetadataModel modelLazy;

    public DatePickerMetadataView() {
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        DefaultDateMetadata metaDataDisabled = DefaultDateMetadata.builder().disabled(true).build();
        model = new DefaultDateMetadataModel();
        model.add(start.plusDays(start.getMonthValue() + 3), metaDataDisabled);
        model.add(start.plusDays(start.getMonthValue() + 6), metaDataDisabled);
        model.add(start.plusDays(start.getMonthValue() + 9), metaDataDisabled);

        modelLazy = new LazyDateMetadataModel() {
            @Override
            public void loadDateMetadata(LocalDate start, LocalDate end) {
                add(start.plusDays(start.getMonthValue() + 2), metaDataDisabled);
                add(start.plusDays(start.getMonthValue() + 5), metaDataDisabled);
                add(start.plusDays(start.getMonthValue() + 8), metaDataDisabled);
            }
        };
    }

    public void onViewChange(DateViewChangeEvent event) {
        String summary = "Year: " + event.getYear() + ", month: " + event.getMonth();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public LocalDate getDate1() {
        return date1;
    }

    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

    public LocalDate getDate3() {
        return date3;
    }

    public void setDate3(LocalDate date3) {
        this.date3 = date3;
    }

    public LocalDate getDate4() {
        return date4;
    }

    public void setDate4(LocalDate date4) {
        this.date4 = date4;
    }

    public DateMetadataModel getModel() {
        return model;
    }

    public DateMetadataModel getModelLazy() {
        return modelLazy;
    }
}
