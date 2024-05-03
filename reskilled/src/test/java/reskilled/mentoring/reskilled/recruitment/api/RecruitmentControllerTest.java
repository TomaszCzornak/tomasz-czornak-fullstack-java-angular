package reskilled.mentoring.reskilled.recruitment.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reskilled.mentoring.reskilled.Utils.RecruitmentStub;
import reskilled.mentoring.reskilled.recruitment.model.request.RecruitmentRequest;
import reskilled.mentoring.reskilled.recruitment.model.response.RecruitmentResponse;
import reskilled.mentoring.reskilled.recruitment.service.RecruitmentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RecruitmentControllerTest {
    @Mock
    private RecruitmentService recruitmentService;
    @InjectMocks
    private RecruitmentController recruitmentController;

    @Test
    void getAllRecruitments_shouldReturnAllRecruitments() {
        //given
        List<RecruitmentResponse> recruitments = RecruitmentStub.createRecruitmentResponseList();
        given(recruitmentService.getAllRecruitments()).willReturn(recruitments);
        //when
        List<RecruitmentResponse> actualRecruitments = recruitmentController.getAllRecruitments();
        //then
        assertEquals(recruitments, actualRecruitments);

    }

    @Test
    void getRecruitmentById_shouldReturnRecruitment() {
        //given
        Long id = 1L;
        RecruitmentResponse recruitment = RecruitmentStub.createRecruitmentResponse();
        given(recruitmentService.getRecruitmentById(id)).willReturn(recruitment);
        //when
        RecruitmentResponse actualRecruitment = recruitmentController.getRecruitmentById(id);
        //then
        assertEquals(recruitment, actualRecruitment);
    }

    @Test
    void addRecruitment_shouldAddRecruitment() {
        //given
        RecruitmentRequest recruitmentRequest = RecruitmentStub.createRecruitmentRequest();
        doNothing().when(recruitmentService).addRecruitment(recruitmentRequest);
        //when
        recruitmentController.addRecruitment(recruitmentRequest);
        //then
        verify(recruitmentService).addRecruitment(recruitmentRequest);
    }

    @Test
    void updateRecruitment() {
        //given
        Long id = 1L;
        RecruitmentRequest recruitmentRequest = RecruitmentStub.createRecruitmentRequest();
        doNothing().when(recruitmentService).updateRecruitment(recruitmentRequest);
        //when
        recruitmentController.updateRecruitment(recruitmentRequest);
        //then
        verify(recruitmentService).updateRecruitment(recruitmentRequest);
    }

    @Test
    void deleteRecruitment() {
        //given
        Long id = 1L;
        doNothing().when(recruitmentService).deleteRecruitment(id);
        //when
        recruitmentController.deleteRecruitment(id);
        //then
        verify(recruitmentService).deleteRecruitment(id);
    }
}