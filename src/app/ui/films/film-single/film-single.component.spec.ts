import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmSingleComponent } from './film-single.component';

describe('FilmSingleComponent', () => {
  let component: FilmSingleComponent;
  let fixture: ComponentFixture<FilmSingleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilmSingleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilmSingleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
