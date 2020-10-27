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
package org.primefaces.showcase.view.data.datatable;

import java.util.Comparator;
import java.util.Objects;

import org.primefaces.model.SortOrder;
import org.primefaces.showcase.domain.Customer;
import org.primefaces.showcase.domain.Customer;

public class LazySorter implements Comparator<Customer> {

    private final String sortField;
    private final SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Customer customer1, Customer customer2) {
        try {
            Object value1 = customer1.getClass().getField(this.sortField).get(customer1);
            Object value2 = customer2.getClass().getField(this.sortField).get(customer2);

            int value = String.valueOf(value1).compareTo(String.valueOf(value2));

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }

}
