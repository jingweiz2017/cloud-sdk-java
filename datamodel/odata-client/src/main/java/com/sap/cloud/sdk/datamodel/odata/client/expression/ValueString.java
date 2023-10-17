/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */

package com.sap.cloud.sdk.datamodel.odata.client.expression;

import javax.annotation.Nonnull;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

/**
 * OData filter expression operand of type Edm.String
 */
public interface ValueString
    extends
    Expressions.OperandSingle,
    FilterableString,
    FilterableComparisonAbsolute,
    FilterableComparisonRelative
{
    /**
     * Null value for string operations.
     */
    @Nonnull
    ValueString NULL = Expressions.OperandSingle.NULL::getExpression;

    @Nonnull
    static ValueString literal( @Nonnull final String v )
    {
        final String escapedValue = v.replaceAll("'", "''");
        return ( protocol, prefixes ) -> "'" + escapedValue + "'";
    }

    @RequiredArgsConstructor
    class Expression implements FilterExpression, ValueString
    {
        @Delegate
        private final FilterExpression delegate;
    }
}