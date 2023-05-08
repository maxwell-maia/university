function grayscale = pic2grayscale(image)
    %Convert image to grayscale
    R = image(:, :, 1);
    G = image(:, :, 2);
    B = image(:, :, 3);
    grayscale = 0.2989 * R + 0.5870 * G + 0.1140 * B;
end