import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpotyfyRegisterComponent } from './spotyfy-register.component';

describe('SpotyfyRegisterComponent', () => {
  let component: SpotyfyRegisterComponent;
  let fixture: ComponentFixture<SpotyfyRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SpotyfyRegisterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpotyfyRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
