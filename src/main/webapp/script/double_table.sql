CREATE TABLE placebackup LIKE place; 
INSERT placebackup SELECT * FROM place;