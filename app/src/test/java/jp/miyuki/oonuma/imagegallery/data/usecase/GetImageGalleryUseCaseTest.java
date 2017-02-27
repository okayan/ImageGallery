package jp.miyuki.oonuma.imagegallery.data.usecase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import jp.miyuki.oonuma.imagegallery.domain.repository.FlickrRepository;

public class GetImageGalleryUseCaseTest {

    private GetImageGalleryUseCase usecase;

    @Mock
    private FlickrRepository flickerRepository;

    @Mock
    private FlickrRepository.FlickrCallback callback;

    @Before
    public void setUp() throws Exception {
        usecase = new GetImageGalleryUseCase(flickerRepository);
    }

    @Test
    public void execute_() throws Exception {

        flickerRepository.fetchFlickr(callback);

    }

}