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
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.datepicker.DateMetaDataModel;
import org.primefaces.model.datepicker.DefaultDateMetaData;
import org.primefaces.model.datepicker.DefaultDateMetaDataModel;
import org.primefaces.model.datepicker.LazyDateMetaDataModel;

@Named
@ViewScoped
public class DatePickerMetaDataView implements Serializable {

    private LocalDate date1;
    private LocalDate date2;
    private final DateMetaDataModel model;
    private final DateMetaDataModel modelLazy;

    public DatePickerMetaDataView() {
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        DefaultDateMetaData metaDataDisabled = DefaultDateMetaData.builder().disabled(true).build();
        model = new DefaultDateMetaDataModel();
        model.add(start.plusDays(start.getMonthValue() + 3), metaDataDisabled);
        model.add(start.plusDays(start.getMonthValue() + 6), metaDataDisabled);
        model.add(start.plusDays(start.getMonthValue() + 9), metaDataDisabled);

        modelLazy = new LazyDateMetaDataModel() {
            @Override
            public void loadDateMetaData(LocalDate start, LocalDate end) {
                add(start.plusDays(start.getMonthValue() + 2), metaDataDisabled);
                add(start.plusDays(start.getMonthValue() + 5), metaDataDisabled);
                add(start.plusDays(start.getMonthValue() + 8), metaDataDisabled);
            }
        };
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

    public DateMetaDataModel getModel() {
        return model;
    }

    public DateMetaDataModel getModelLazy() {
        return modelLazy;
    }
}
