-- Based on: https://gist.github.com/voskobovich/537b2000108e4781f70b

-- Notes: In an ideal world, currencies would exist in a separate schema, however it's not yet support
-- and since the recommend time to implement the solution is 1 hour, have opted to dump it here
-- for simplicity
CREATE TABLE IF NOT EXISTS organisations_schema.currencies (
  name   VARCHAR(20),
  -- ISO code
  code   VARCHAR(3) PRIMARY KEY,
  symbol VARCHAR(5)
);


INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Leke', 'ALL', 'Lek') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'USD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Afghanis', 'AFN', '؋') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pesos', 'ARS', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Guilders', 'AWG', 'ƒ') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'AUD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('New Manats', 'AZN', 'ман') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'BSD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'BBD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rubles', 'BYR', 'p.') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Euro', 'EUR', '€') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'BZD', 'BZ$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'BMD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Bolivianos', 'BOB', '$b') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Convertible Marka', 'BAM', 'KM') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pula', 'BWP', 'P') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Leva', 'BGN', 'лв') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Reais', 'BRL', 'R$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pounds', 'GBP', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'BND', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Riels', 'KHR', '៛') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'CAD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'KYD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pesos', 'CLP', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Yuan Renminbi', 'CNY', '¥') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pesos', 'COP', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Colón', 'CRC', '₡') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Kuna', 'HRK', 'kn') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pesos', 'CUP', '₱') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Koruny', 'CZK', 'Kč') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Kroner', 'DKK', 'kr') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pesos', 'DOP ', 'RD$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'XCD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pounds', 'EGP', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Colones', 'SVC', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pounds', 'FKP', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'FJD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Cedis', 'GHC', '¢') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pounds', 'GIP', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Quetzales', 'GTQ', 'Q') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pounds', 'GGP', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'GYD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Lempiras', 'HNL', 'L') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'HKD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Forint', 'HUF', 'Ft') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Kronur', 'ISK', 'kr') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rupees', 'INR', 'Rp') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rupiahs', 'IDR', 'Rp') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rials', 'IRR', '﷼') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pounds', 'IMP', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('New Shekels', 'ILS', '₪') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'JMD', 'J$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Yen', 'JPY', '¥') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pounds', 'JEP', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Tenge', 'KZT', 'лв') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Won', 'KPW', '₩') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Won', 'KRW', '₩') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Soms', 'KGS', 'лв') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Kips', 'LAK', '₭') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Lati', 'LVL', 'Ls') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pounds', 'LBP', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'LRD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Switzerland Francs', 'CHF', 'CHF') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Litai', 'LTL', 'Lt') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Denars', 'MKD', 'ден') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Ringgits', 'MYR', 'RM') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rupees', 'MUR', '₨') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pesos', 'MXN', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Tugriks', 'MNT', '₮') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Meticais', 'MZN', 'MT') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'NAD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rupees', 'NPR', '₨') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Guilders', 'ANG', 'ƒ') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'NZD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Cordobas', 'NIO', 'C$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Nairas', 'NGN', '₦') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Krone', 'NOK', 'kr') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rials', 'OMR', '﷼') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rupees', 'PKR', '₨') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Balboa', 'PAB', 'B/.') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Guarani', 'PYG', 'Gs') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Nuevos Soles', 'PEN', 'S/.') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pesos', 'PHP', 'Php') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Zlotych', 'PLN', 'zł') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rials', 'QAR', '﷼') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('New Lei', 'RON', 'lei') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rubles', 'RUB', 'руб') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pounds', 'SHP', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Riyals', 'SAR', '﷼') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dinars', 'RSD', 'Дин.') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rupees', 'SCR', '₨') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'SGD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'SBD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Shillings', 'SOS', 'S') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rand', 'ZAR', 'R') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rupees', 'LKR', '₨') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Kronor', 'SEK', 'kr') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'SRD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pounds', 'SYP', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('New Dollars', 'TWD', 'NT$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Baht', 'THB', '฿') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'TTD', 'TT$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Lira', 'TRY', '₺') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Liras', 'TRL', '£') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dollars', 'TVD', '$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Hryvnia', 'UAH', '₴') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Pesos', 'UYU', '$U') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Sums', 'UZS', 'лв') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Bolivares Fuertes', 'VEF', 'Bs') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Dong', 'VND', '₫') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rials', 'YER', '﷼') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Zimbabwe Dollars', 'ZWD', 'Z$') ON CONFLICT DO NOTHING;
INSERT INTO organisations_schema.currencies (name, code, symbol) VALUES ('Rupees', 'INR', '₹') ON CONFLICT DO NOTHING;