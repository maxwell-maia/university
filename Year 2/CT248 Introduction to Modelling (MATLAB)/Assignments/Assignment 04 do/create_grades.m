function grades = create_grades(students, subjects, lower, upper, seed)
    rng(seed);
    grades = randi([lower upper], students, subjects);
end