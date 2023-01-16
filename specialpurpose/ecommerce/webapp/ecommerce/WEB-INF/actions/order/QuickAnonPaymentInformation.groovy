/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/* This file has been modified by Open Source Strategies, Inc. */

import org.ofbiz.entity.*;
import org.ofbiz.entity.util.*;
import org.ofbiz.base.util.*;
import org.ofbiz.accounting.payment.*;
import org.ofbiz.order.shoppingcart.*;
import org.ofbiz.party.contact.*;

cart = ShoppingCartEvents.getCartObject(request);
context.cart = cart;

paymentMethodTypeId = parameters.paymentMethodTypeId ?: "CREDIT_CARD";

// nuke the event messages
request.removeAttribute("_EVENT_MESSAGE_");

if (cart?.getPaymentMethodIds()) {
    paymentMethods = cart.getPaymentMethods();
    paymentMethods.each {paymentMethod ->
        if ("CREDIT_CARD".equals(paymentMethod?.paymentMethodTypeId)) {
            paymentMethodId = paymentMethod.paymentMethodId;
            parameters.paymentMethodId = paymentMethodId;
        }
    }
}

