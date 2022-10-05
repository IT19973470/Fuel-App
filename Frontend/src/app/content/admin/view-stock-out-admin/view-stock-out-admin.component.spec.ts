import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewStockOutAdminComponent } from './view-stock-out-admin.component';

describe('ViewStockOutAdminComponent', () => {
  let component: ViewStockOutAdminComponent;
  let fixture: ComponentFixture<ViewStockOutAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewStockOutAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewStockOutAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
