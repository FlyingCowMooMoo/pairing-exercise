CREATE TABLE IF NOT EXISTS organisations_schema.invoice
(
    id              UUID DEFAULT UUID_GENERATE_V4() PRIMARY KEY,
    organisation_id UUID               NOT NULL,
    created_date    DATE DEFAULT NOW() NOT NULL,
    due_date        DATE               NOT NULL,
    currency_iso TEXT REFERENCES organisations_schema.currencies (code) NOT NULL
);

CREATE TABLE IF NOT EXISTS organisations_schema.invoice_item
(
    id            UUID DEFAULT UUID_GENERATE_V4() PRIMARY KEY,
    invoice_id    UUID REFERENCES organisations_schema.invoice (id ) NOT NULL,
    description   TEXT    NOT NULL,
    quantity      NUMERIC NOT NULL,
    cost_per_unit DECIMAL  NOT NULL,
    discount      DECIMAL  NULL
);

CREATE TABLE IF NOT EXISTS organisations_schema.invoice_note
(
    id          UUID DEFAULT UUID_GENERATE_V4() PRIMARY KEY,
    invoice_id  UUID REFERENCES organisations_schema.invoice (id) NOT NULL,
    description TEXT NOT NULL
);


CREATE TABLE IF NOT EXISTS organisations_schema.invoice_payment
(
    id             UUID DEFAULT UUID_GENERATE_V4() PRIMARY KEY,
    invoice_id     UUID REFERENCES organisations_schema.invoice (id) NOT NULL,
    paid           BOOL DEFAULT FALSE,
    payment_method TEXT               NOT NULL,
    payment_date   DATE DEFAULT NOW() NOT NULL
);

-- Ideally when time allows, would have liked to implement part payment on an invoice,
-- since invoices are not always paid in full all at once
CREATE UNIQUE INDEX invoice_invoice_payment ON organisations_schema.invoice_payment (invoice_id)

