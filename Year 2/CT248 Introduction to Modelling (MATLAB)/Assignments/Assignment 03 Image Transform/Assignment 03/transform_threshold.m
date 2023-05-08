function imgOut = transform_threshold(img, threshold)
    %Convert grayscale image to binary, where the pixels of intensity
    %higher than the threshold are white, and every other pixel
    %is black
    imgOut = (img > threshold);
end