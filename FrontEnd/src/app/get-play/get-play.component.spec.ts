import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPlayComponent } from './get-play.component';

describe('GetPlayComponent', () => {
  let component: GetPlayComponent;
  let fixture: ComponentFixture<GetPlayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetPlayComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetPlayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
