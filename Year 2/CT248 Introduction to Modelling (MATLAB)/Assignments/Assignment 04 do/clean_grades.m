function grades = clean_grades(grades)
    inv_grades = grades < 0 | grades > 100;
    grades(inv_grades) = 0;
end